#! /bin/bash -eu

#docker run --rm -i \
#  --workdir /mnt \
#  -v $(pwd):/mnt \
#  busybox:1.32 \
#  -- sh -c "cat /tmp/text-input.txt; echo 'Container' > /tmp/text-output.output"
#  $@

docker run --rm -i --workdir //c/Users/Dell --volume //c/Users/Dell:/tmp busybox:1.32 sh -c "echo 'Container' >> /tmp/text-output.output; cat /tmp/text-output.output"
echo "find the result in //c/Users/Dell"
