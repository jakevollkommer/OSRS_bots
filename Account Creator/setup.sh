# Install phantomjs
sudo wget https://bitbucket.org/ariya/phantomjs/downloads/phantomjs-2.1.1-linux-x86_64.tar.bz2
sudo mkdir /opt/phantomjs
bzip2 -d phantomjs-2.1.1-linux-x86_64.tar.bz2
sudo tar -xvf phantomjs-1.9.8-linux-x86_64.tar --directory /opt/phantomjs/ --strip-components 1
sudo ln -s /opt/phantomjs/bin/phantomjs /usr/bin/phantomjs
sudo apt-get install libfontconfig

# Install pip
curl -O https://bootstrap.pypa.io/get-pip.py
python3 get-pip.py --user
echo 'export PATH=LOCAL_PATH:$PATH' >> ~/.bash_profile
source ~/.bash_profile

# Install dependencies
pip install awsebcli --upgrade --user
pip install selenium
pip install pypandoc
pip install randomwordgenerator
