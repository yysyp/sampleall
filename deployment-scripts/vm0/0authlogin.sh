#! /bin/bash
set -o nounset
set -o errexit

current_datetime_str=$(date +"%Y-%m-%d %H:%M:%S")

if [ ! -e "./authLogin.output" ]; then
    touch ./authLogin.output
    echo "$current_datetime_str" > ./authLogin.output
fi

last_datetime_str=`cat ./authLogin.output`
last_datetime=$(date -d "$last_datetime_str" +%s)
current_datetime=$(date -d "$current_datetime_str" +%s)
if (( $current_datetime - $last_datetime < 86400 )); then
  echo "Auth within 1 day."
else
  echo "To login"
  gcloud config set project xxx
  gcloud auth login
  echo "$current_datetime_str" > ./authLogin.output
fi
