
# Gerdarzh

## An application about the celtic languages
The application Gerdarzh is a dictionary and mini-game around the theme of celtic languages (Gaulish, Irish, Breton and Welsh essentially). The project is currently under construction and is not fully runnable for now.

Gerdarzh constists of a consultative part with a lexic of celtic words with french and english translations, as well as their type, genre, phonetic form, and eventually the etymological link with other celtic words.

The other part is a mini-game which consists of guessing the literal meaning of a Celtic word or of Celtic origin, generally proper nouns.


## Technical information
After the launch of the application, a local server is open at : http://localhost:8080/
Default login:
"email": "test"
"password": "pass"

The application is web oriented with an MVC pattern (model, view, controller).
The code is composed of an html/javascript/CSS interface layer, a Java layer for the data treatment, and a mySql database who persist the data.

The java layer is essentially build with the framework Spring and use the Java Persistence API (JPA) to organise relational data.

## Deployed Version (Work In Progress)
A work in progress version (v:0.3) is deployed on the below AWS address, some functionalities may are not available according to the actual deployed version: http://ec2-16-171-253-51.eu-north-1.compute.amazonaws.com:8080/login "email": "test" "password": "pass"

## How the project is developed ?
### Project management with SRUM method on Jira:
https://riwald.atlassian.net/jira/software/projects/LGER/boards/4/backlog?atlOrigin=eyJpIjoiZGM1NTljZjgyNGU5NDg0Nzg3MmVlYWFjMjliMjA1YmQiLCJwIjoiaiJ9

### Front end model with figma:
https://www.figma.com/file/dwh8Ie4gIk0RLJh6CV08Yf/Figma-basics?type=design&node-id=1669%3A162202&mode=design&t=3xnStdUY5267NGxx-1


## Why?
The purpose of this project is to share and understand deeper the original sense of the celtic words used today in the celtic languages.

