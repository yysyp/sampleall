#! /bin/bash
set -o nounset
set -o errexit

echo "login 1"
./login.sh

echo "login 2"
./login.sh

echo "login 3"
./login.sh

