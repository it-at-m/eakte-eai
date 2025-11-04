# eakte-eai


Die EAkte EAI ist die Schnittstelle für Fachanwendungen der EAkte zum DMS über REST.

Eine aktuelle übersicht über alle Schnittstelle kann über Swagger bezogen werden: TODO Link auf Swagger URL in der CAP


### Built With

This project is built with the following technologies:

* Java
* Spring Boot
* Maven

## Roadmap

*This project is currently being developed.*

## Set up

### Running locally

Build the application by using `mvn install`

The resulting JAR-File can be run using `java -jar <path_to_the_jar_file>`

### Running with podman

Create the Dir: eakte-eai/eakte-external-config
Put here the file the application.yml file with the correct data.
Start a console in the dir: eakte-eai
call "podman compose up"
Now the container should start.
Test this with in you browser with: http://localhost:8080/swagger-ui/index.html

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please open an issue with the tag "enhancement", fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

More about this in the [CODE_OF_CONDUCT](/CODE_OF_CONDUCT.md) file.

## License

Distributed under the MIT License. See [LICENSE](LICENSE) file for more information.

## Contact

it@M - opensource@muenchen.de
