# DemoJavaHTTP
A simple HTTP server for crud operations and authorizations.

This is not a template and should probably not be used like one as this does not follow any best practices of Java HTTP development, I just threw something together to use as a demo of http with java.

## Endpoints

### /
"Hello world"

### /secret
header Authorization: basgrupp4

### /database
* GET  
returns all entries
* POST  
The text in the BODY will be added as a new entry

### /database/id
* GET  
returns the entry at id
* PUT  
updates the entry at id, the text in the BODY will overwrite the old text.
* DELETE  
deletes the entry at id
