#!/bin/sh

#################################################################################
#                                    WARNING                                    #
#                                                                               #
#                             YOU MUST DO THIS FIRST!                           #
#                                                                               #
# Make sure "All traffic" is enabled in the security group of this EC2 instance #
#################################################################################

sudo apt-get update
sudo apt install xrdp -y
sudo systemctl enable xrdp
sudo add-apt-repository ppa:gnome3-team/gnome3
sudo apt-get install gnome-shell ubuntu-gnome-desktop
sudo passwd ubuntu
echo - "Exit the instance with `exit`"
