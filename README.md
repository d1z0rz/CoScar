# Coscar
JAVA based programm, that calculates trip's **time** and **distance** based between A and B place traveled by car/bike/walk and show **weather** in B place.
## Using
You should download latest jar from  [JSON page](https://github.com/stleary/JSON-java)
Program was tested in [IntelliJ IDEA](https://www.jetbrains.com/idea/)
### How does program work?
Input data -> [Google Matrix API](https://developers.google.com/maps/documentation/distance-matrix/intro) -> if everything is fine ->  [APIXU weather API](https://www.apixu.com/api.aspx) -> display data
### Everything about classes
* `urlBulder` is used for building URL link for Google API
* `urlWather` is used for building URL link for APIXU API
* `PäringuSõne` is used for attaching query(es) to URL link for API(s)
* `HttpÜhendus` is used for sending GET request and reading recieved JSON database
* `SõnumiGenereerija` is used for generating random message before closing
* `Main` is used for communicating with user
