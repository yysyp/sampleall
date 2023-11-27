#! /bin/bash -eu

MAVEN_IMAGE="xxx"

docker run -i --rm --workdir /mnt \
  --volume $(pwd):/mnt \
  --volume /build/.m2 \
  --env credentials=${xxxcredentials} \
  ${MAVEN_IMAGE} $@