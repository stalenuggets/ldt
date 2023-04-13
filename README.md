<img align = "right" src = "app/src/main/res/drawable/icon.png" width = "75" />

# Legally Distinct Tamagotchi
CST 338 - Project 2: Android Application


## Student Info
* Erika Iwata
* CRN: 23026
* Spring 2023


## Program Description
This is an android application of a tamagotchi clone.

Required Use Cases:
- [X] Predefined Users
- [X] Persistence
- [X] Add a User
- [X] Delete a User

Additional Use Cases (Mostly based on [User Manual](assets/instructions.png)):
- [ ] Feed
- [ ] Lights
- [ ] Game
- [ ] Sick/Care
- [ ] Bathroom
- [ ] Health
- [ ] Discipline
- [ ] Attention
- [ ] Set Tamagotchi Rarity
- [X] Add Admin user

See also: [Project 2: Use Case Document](https://docs.google.com/document/d/1dKzxhwV3vJF8Jh_HL8r1ABE1OW-fONaGkUaXyQGTOvY/edit#)

## Video Demonstration
[Insert Youtube Link]


## Software Engineering
This section describes some of the software engineering techniques used for the design and development of this program.

Firstly, it performs CRUD operations using the Room persistence library on Android Studio. Room provides an abstraction layer over SQLite to store persisting data locally. This was used to store user info, tamagotchi rarity, and tamagotchi health stats in the following files:
```
AppDatabase.java
Health.java
HealthDao.java
Rarity.java
RarityDao.java
User.java
UserDao.java
```

## Directions and Grading Rubric
Review the following: [Project 2: Android Application](https://docs.google.com/document/d/11b4FGL7AFz1h61ElDy7lOoifnz2GdMy54rca-w9wrMU/edit?usp=sharing)
