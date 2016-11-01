#!/bin/sh

docker run \
	--name sameness \
	--interactive \
	--tty \
	mvn deploy \
	--settings settings.xml
