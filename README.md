# World Cup Scoreboard

An interactive application for tracking live World Cup football matches and scores.

# Overview
This application represents a football scoreboard system, which allows users to
- Starting new matches
- Updating scores in real-time
- Finishing matches once completed
- Viewing a summary of all current matches

# Assumptions

During implementation, I made few assumptions:
- it is possible that the score can be updated into lower value (in case of a mistake or a cancellation of goal by VAR)
- it is not possible to update score more than 1 goal at one time
- finish the match is implemented as soft delete from the in memory db, should not be a problem as currently World Cup has only 64 games
- get summary shows only ongoing matches
- matches with reversed home and away teams are consider different matches therefore even if there is ongoing
Spain - France match, operations on a France - Spain match will result with MatchNotFound exception 

# Technical information

## Technical approach 
Application was written using Test Driven Development and because of that there are multiple commits that 
have red tests. Ideally it should never happen, but I wanted to showcase the process of writing tests first.

## Architecture
Application was written using hexagonal architecture. It is not the simplest solution possible but hexagonal architecture has a lot of benefit when it comes to writing code using TDD approach therefore I choose 
to have a bit of overengineered architecture.

## Database
Data is stored in in-memory database implemented using ArrayList to make it simple, but it was also
written in a way that it could be easily replaced database connection with changing only repository 
and configuring the connection.

 
