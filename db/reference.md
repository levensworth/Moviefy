# Reference for database csv tables

The delimiter used to separete the values is the semicolon ";".

## movieDB

* id
* movieTitle
* directorFk
* actor1Fk
* actor2Fk
* actor3Fk
* duration
* year
* genres              *Genres values are separated with pipe char "|"*
* imdbScore
* numCriticForReviews
* parentalGuidance
* language
* country
* tags                *Tag values are separated with pipe char "|"*
* imdbLink

## personDB

* id
* name
* fkMoviesAsActor     *Fk values are separated with pipe char "|"*
* fkMoviesAsDirector  *Fk values are separated with pipe char "|"*
