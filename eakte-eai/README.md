# eakte-eai

Die EAkte EAI ist die Schnittstelle für Fachanwendungen der EAkte zum DMS über REST.

Eine aktuelle übersicht über alle Schnittstelle kann über Swagger bezogen werden: TODO Link auf Swagger URL in der CAP

## Set Up Container with Github-Image

### Step One - Configure external application.yml file

1. Go inside the folder `/eakte-eai`
2. Create a new folder called `/eakte-external-config`
3. Inside that new created folder create a new file called `application.yml`
4. After creating this file copy this in your File:

```yaml
dms:
  eai:
    service:  #  EAI <--> DMS
      url: # URL for Fabasoft-System
      username: # Username for Fabasoft-System
      password: # Password for Fabasoft-System
  # Standardnamensbereich für Anweisungen an Fabasoft
  default:
    operation:
      namespace: 
  # DMS url
  url: 
  auth:
    bcrypt:
      strength: 10
    users:
      - username: # create an Username which has to be taken to login to the API - e.g. testuser
        password: # create a Password. The Password has to be bcrypt with factor 10. Paste it here. Later you also need your password to login to the API - e.g. '$2y$10$l8eaMjg0Co0T7npuD25sHePTq5P/M.mRby5p6I7XiZnZyr/hZOWee' - bcrypt for "testpw"
        roles:
          - "BASIC"
    roles:
      BASIC: WRITE, READ
```

### Step Two - Configure the docker-compose.yml

1. Navigate into the folder `/eakte-eai`
```bash
cd /eakte-eai
ls
# You should see a file called docker-compose.yml
```

2. Configure the file like shown below:

```yaml
version: "3.1"
services:
  eakte-eai-adapter:
    # start with image built within GiHub build pipeline:
    # The image should be the right one. For more information check: https://github.com/it-at-m/eakte-eai/pkgs/container/eakte-eai%2Feakte-eai
    image: ghcr.io/it-at-m/eakte-eai/eakte-eai:dev
    hostname: eakte-eai-adapter
    ports:
      - 8080:8080
    volumes:
      - ./eakte-external-config:/deployments/eakte-external-config
    # In case you need a proxy add this part to your docker-compose.yml. If you don't need a proxy simply remove environment: JAVA_TOOL_OPTIONS: -Dhttp.proxyHost=yourProxy.com -Dhttp.proxyPort=Port
    environment:
     JAVA_TOOL_OPTIONS: -Dhttp.proxyHost=yourProxy.com -Dhttp.proxyPort=Port

  volumes:
    external-config-volume:
      external: true
```

### Step Three - Start Container

Once you have inserted all necessary URLs, usernames, and passwords in `/eakte-external-config/application.yml` you can start your container.

For this example, we will use `podman-compose`, but you can also use `docker-compose` if preferred.

Open your terminal and navigate to the `/eakte-eai` directory, where the `docker-compose.yml` file is located.

   ```bash
   cd /eakte-eai
   ls
   # you should see the file docker-compose.yml
   ```

To start the container, simply run the following command:
```bash
podman-compose up
```

This command will instruct Podman to load the image from GitHub and start the container.

After the container has started, you can verify that the Swagger-UI is accessible. In our case it was [localhost:8080/swagger-ui/index.html](localhost:8080/swagger-ui/index.html) (This link may be different in your case)

If the Swagger UI loads successfully, click the green button at the top of the page labeled "Authorize." Here, you can enter the username and password which you put in `/eakte-external-config/application.yml`

Once you have logged in to the API, you can begin sending your first requests.