#!/usr/bin/python3

import math

from selenium import webdriver
from random import random
from randomwordgenerator import randomwordgenerator

def init_driver(url):
    # Use custom chrome options
    chrome_options = webdriver.ChromeOptions()
    chrome_options.add_argument('--headless')
    chrome_options.add_argument('--no-sandbox')
    chrome_options.add_argument('--disable-dev-shm-usage')
    chrome_driver_binary='/usr/bin/chromium-browser'
    driver = webdriver.Chrome(chrome_driver_binary, chrome_options=chrome_options)
    driver.get(url)

    return driver

def create_account(driver, email, password):
    # Get all the elements for form submission
    email_field = driver.find_element_by_xpath('//*[@id="create-email"]')
    password_field = driver.find_element_by_xpath('//*[@id="create-password"]')

    day_field = driver.find_element_by_xpath('//*[@id="create-email-form"]/fieldset/div/label[1]/input')
    month_field = driver.find_element_by_xpath('//*[@id="create-email-form"]/fieldset/div/label[2]/input')
    year_field = driver.find_element_by_xpath('//*[@id="create-email-form"]/fieldset/div/label[3]/input')

    submit = driver.find_element_by_xpath('//*[@id="create-submit"]')

    # Submit the form
    email_field.send_keys(email)
    password_field.send_keys(email)
    day_field.send_keys(random.randint(1,30))
    month_field.send_keys(random.randint(1,12))
    year_field.send_keys(random.randint(1995,2005))

    submit.click()

def generate_email(random_words=2):
    # Generate random words to create the email
    random_words = randomwordgenerator.generate_random_words(n = random_words)
    random_number = random.randrange(0,1000)

    # Use n number of random words, separated by '.' and a random number to
    # generate a pseudo-email
    email = '.'.join(random_words)
    email += random_number

    return email
