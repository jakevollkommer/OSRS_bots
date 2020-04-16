#!/usr/bin/python3

import account_creator

# Account Creation URl
url= 'https://secure.runescape.com/m=account-creation/create_account'

# Default account password
password = 'ordo_ab_chao'

driver = account_creator.init_driver(url)
email = account_creator.generate_email()
account_creator.create_account(driver, email, password)
