#!/bin/sh

aws --profile local --endpoint-url http://localhost:4566 sns create-topic --name foo
