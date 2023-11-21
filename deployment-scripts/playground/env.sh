#! /bin/bash
set -o nounset
set -o errexit


loginFun() {
  if [ ! -e "./loginStatus2" ]; then
    echo 'Not exists, create loginStatus2'
    touch ./loginStatus2
  fi

  loginStatus2=`cat ./loginStatus2`
  if [ -n "$loginStatus2" ]; then
    echo "$loginStatus2 not Empty or null"
    exit 0
  fi

  echo "!!!To Login!!!"
  echo "true" > ./loginStatus2
}

echo "loginFun 1 --- "
loginFun

echo "loginFun 2 --- "
loginFun


