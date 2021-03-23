#!/bin/sh

aws --profile local --endpoint-url http://localhost:4566\
  sns create-topic\
  --name chicken_topic

aws --profile local --endpoint-url http://localhost:4566\
  sqs create-queue\
  --queue-name badger_queue

aws --profile local --endpoint-url http://localhost:4566\
  sns subscribe\
  --topic-arn "arn:aws:sns:eu-west-1:000000000000:chicken_topic"\
  --protocol sqs\
  --notification-endpoint "arn:aws:sqs:eu-west-1:000000000000:badger_queue"

aws --profile local --endpoint-url http://localhost:4566\
  sqs create-queue\
  --queue-name unicorn_queue

aws --profile local --endpoint-url http://localhost:4566\
  sns subscribe\
  --topic-arn "arn:aws:sns:eu-west-1:000000000000:chicken_topic"\
  --protocol sqs\
  --notification-endpoint "arn:aws:sqs:eu-west-1:000000000000:unicorn_queue"
