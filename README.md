# Badminton Tournament Companion
This is a Badminton Tournament Companion App. It is designed to work with the Badminton Tournament Planner. The runs on Android, iOS and Windows Phone. 

## Target Audience
* Clubs which want to offer an app to the players. 
* Individuals which organize a tournament

## Core Features
* Push Notifications to players prior to the game (5 Minutes)
* Push Notifications for announcements
* Tournament standings
* Automatic Synchronisation with "Badminton Tournament Planner"
* Various static announcements (prices for food etc.)

## Architecture
The Tournament Companion consists of two parts:
* Companion Server: It's task is to provide an interface for the app to get all the data. It parses the Tournament Planner file and provides services for the Companion Client. The Server runs on the same machine as the Tournament Planner. The address of this machine is configured in the Client. Thus it should run some sort of DynDNS. 
* Companion Client: The client is a app (Windows Phone, iOs or Android) which shows the information. It is built via Phonefactor. This allows to have the App be generated from general purpose web code

