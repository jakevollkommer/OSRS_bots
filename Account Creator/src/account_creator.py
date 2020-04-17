from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import requests
import argparse
import random
import time


random_word_site = "http://svnweb.freebsd.org/csrg/share/dict/words?view=co&content-type=text/plain"


def generate_random_emails(n):
    response = requests.get(random_word_site)
    words = random.sample([x for x in response.content.splitlines() if 3 < len(x) < 8], 2)
    emails = ["{}.{}{}@gmail.com".format(words[0], words[1], i) for i in range(n)]

    return emails


def generate_random_passwords(n):
    response = requests.get(random_word_site)
    passwords = random.sample([x for x in response.content.splitlines() if 8 < len(x) < 12], n)

    return passwords


def generate_random_birthdays(n):
    birthdays = [[random.randint(1, 28), random.randint(1, 12), random.randint(1990, 1999)] for _ in range(n)]

    return birthdays


def create_account(email, password, birthday):
    options = Options()
    options.add_argument('--headless')
    options.add_argument('--disable-gpu')
    driver = webdriver.Chrome(executable_path='/users/harrison/Desktop/chromedriver', chrome_options=options)
    driver.get("https://secure.runescape.com/m=account-creation/g=oldscape/create_account")
    time.sleep(3)

    driver.find_element_by_link_text("GOT IT").click()

    input_email = driver.find_element_by_id("create-email")
    input_password = driver.find_element_by_id("create-password")

    input_email.click()
    input_email.send_keys(email)

    input_password.click()
    input_password.send_keys(password)

    input_day = driver.find_element_by_class_name("m-date-entry__day-field")
    input_month = driver.find_element_by_class_name("m-date-entry__month-field")
    input_year = driver.find_element_by_class_name("m-date-entry__year-field")

    input_day.click()
    input_day.send_keys(birthday[0])

    input_month.click()
    input_month.send_keys(birthday[1])

    input_year.click()
    input_year.send_keys(birthday[2])

    create = driver.find_element_by_id("create-submit")
    create.click()


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Bulk RS Account Creator")
    parser.add_argument("--num", type = int)

    args = parser.parse_args()

    emails = generate_random_emails(args.num)
    passwords = generate_random_passwords(args.num)
    birthdays = generate_random_birthdays(args.num)

    target_accounts = dict(zip(emails, zip(passwords, birthdays)))

    for k, v in target_accounts.iteritems():
        create_account(k, v[0], v[1])
