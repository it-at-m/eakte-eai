#!/bin/bash
IMAGE_ID=28c844299f20
IMAGE_NAME="localhost/eakte-rest-eai"
CONTAINER_NAME="eakte-rest-eai"
#podman run -d --name eakte-eai-adapter -p 80:8080 localhost/eakte-rest-eai
# /etc/containers/registries.conf
echo "------------------------------------------------------------------------"
echo "Stopping old container..."
podman container stop "${CONTAINER_NAME}"
echo "------------------------------------------------------------------------"
echo "Deleting old container..."
podman container rm "${CONTAINER_NAME}"
echo "------------------------------------------------------------------------"
echo "Starting new container..."
# --detach
# -it
# -E VAR=value
podman run -it --name "${CONTAINER_NAME}" -p 8080:80 "${IMAGE_ID}"
