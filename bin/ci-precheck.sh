#!/bin/sh -e

PROJECT_VERSION=`mvn help:evaluate -Dexpression=project.version | grep -v '\['`

echo "Project version determined to be ${PROJECT_VERSION}"

if [ -n "${TRAVIS_TAG}" ]; then
	echo "Code tagged at ${TRAVIS_TAG}"

	if [ "${TRAVIS_TAG}" != "${PROJECT_VERSION}" ]; then
		echo >&2 "SCM tag [${TRAVIS_TAG}] doesn't match project version [${PROJECT_VERSION}]."
		exit 1
	fi

fi

export PROJECT_VERSION
