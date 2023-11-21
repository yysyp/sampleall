#! /bin/bash
set -o nounset
set -o errexit

current_datetime_str=$(date +"%Y-%m-%d %H:%M:%S")

#if [ ! -d "./folder" ]; then
if [ ! -e "./authLogin.output" ]; then #check file exists
    echo 'Not exists, create authLogin.output'
    touch ./authLogin.output
    echo "1000-01-01 00:00:00" > ./authLogin.output
fi

last_datetime_str=`cat ./authLogin.output`
last_datetime=$(date -d "$last_datetime_str" +%s)
current_datetime=$(date -d "$current_datetime_str" +%s)
#if [ -n "$authLogin.output" ]; then
#  echo "authLogin.output is Not empty or Null or 0"
if (( $current_datetime - $last_datetime < 86400 )); then
  echo "Valid within 1 day."
#  exit 0
else
  echo "!!!this is child execution!!!"
  echo "$current_datetime_str" > ./authLogin.output
fi

#exit 0