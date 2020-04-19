from ConfigParser import SafeConfigParser
from selenium import webdriver
import pymysql
import requests
import argparse
import random
import time



def db_connect(config):
    server = config.get("database", "server")
    user = config.get("database", "user")
    password = config.get("database", "password")
    db = config.get("database", "db")
    charset = config.get("database", "charset")

    connection = pymysql.connect(host=server, user=user, password=password, db=db, charset=charset)

    return connection


def insert_account(db, email, password):
    query = "insert into accounts (email, password, tutorial_complete) values ('{}', '{}', false)".format(email, password)
    db.cursor().execute(query)
    db.commit()



def generate_random_emails(n, config):
    response = requests.get(config.get("random_words", "url"))
    words = random.sample([x for x in response.content.splitlines() if 3 < len(x) < 8], 2)
    emails = ["{}.{}{}@gmail.com".format(words[0], words[1], i) for i in range(n)]

    return emails


def generate_random_passwords(n, config):
    response = requests.get(config.get("random_words", "url"))
    passwords = random.sample([x for x in response.content.splitlines() if 8 < len(x) < 12], n)

    return passwords


def generate_random_birthdays(n):
    birthdays = [[random.randint(1, 28), random.randint(1, 12), random.randint(1990, 1999)] for _ in range(n)]

    return birthdays


def create_account(db, email, password, birthday):
    driver = webdriver.Chrome(executable_path='/users/harrison/Desktop/chromedriver')
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

    time.sleep(3)

    if 'WELCOME' in driver.page_source:
        insert_account(db, email, passwords)
    else:
        print 'Failed to create account: {}'.format(email)



if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Bulk RS Account Creator")
    parser.add_argument("--num", type = int)

    args = parser.parse_args()
    config = SafeConfigParser()
    config.read("account_creator_config.ini")

    db = db_connect(config)

    emails = generate_random_emails(args.num, config)
    passwords = generate_random_passwords(args.num, config)
    birthdays = generate_random_birthdays(args.num)

    target_accounts = dict(zip(emails, zip(passwords, birthdays)))
    print target_accounts
    #
    for k, v in target_accounts.iteritems():
        create_account(db, k, v[0], v[1])
