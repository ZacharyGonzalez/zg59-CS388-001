# Milestone 1 - Do You Love Me (DYLM) (Unit 7)

## Table of Contents

1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
Demo: https://www.youtube.com/watch?v=U6H-N-57U8M&feature=youtu.be

LINK TO PROJECT REQUIREMENTS: https://github.com/MattToegel/CS388/blob/Unit7/Unit7/project-requirements.md
## Overview

### Description

Do You Love Me is a relationship-focused app designed to enhance emotional connections through personalized reminders, notifications, love letters, and collectable personalized cards. Users can set customizable notifications with thoughtful suggestions, such as "You should send them a message" or "Buy a small gift that reminded you of them."

The app enables users to write love letters, which are displayed in a dedicated feed, and create personalized cards for specific moments or trigger events. It also features a "Big Red Attention Button" that, when pressed, notifies the user's partner (if supported) or displays a pre-written card instead. Unlike love letters, cards are tailored for immediate, situational use and help users express themselves in a meaningful way.

The purposes of this app are:
To offer a gentle way for individuals in a relationship to request attention.
To help couples with emotional communication challenges express themselves through playful love letters and personalized cards that can be collected.
To allow users to set customized notifications and reminders for their spouse, reinforcing how they wish to be loved.
To provide intangible comfort by consistently affirming interest and emotional connection within the relationship.

### App Evaluation

- **Category:** Relationships
- **Mobile:** Designed for ease of use with features like notifications, haptic feedback, camera integration, and accessible navigation
- **Story:** Helps users stay mindful in their relationships, remember thoughtful gestures, and playfully communicate with their significant other.
- **Market:** Suitable for anyone in a relationship, with a focus on individuals who may struggle with emotional signaling or timing, such as those with ADHD or stoic tendencies.
- **Habit:** Users should configure the app to suit their needs, acting on periodic notifications. Users will occasionally access the app to view notes, interact with cards, use the Big Red Button, or modify notifications and reminders..
- **Scope:** The app's core features are simple yet effective, ensuring usability even with minimal functional implementations. The most complex aspect lies in implementing interaction between users for the Big Red Button.

## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

1. User can create an account.
2. User can login.
3. User can create entries.
4. User can view entries.
5. User can view more options for an entry.
6. User can edit entries.
7. User can see analytics.
8. User can press a Big Red Button to get attention.

**Optional Features**

1. Big Red Attention button sends a signal to the spouses app.
2. Cards can include images.

### 2. Screen Archetypes

- LOGIN
  - Log in to an account.
  - Create a new account.

- Dashboard
  - Features the Big Red Attention button
  - Displays a cycling feed of love letters.
  - Includes a mailbox for viewing cards.

- User
  - Displays user metrics.
  - Allows modification of account details and app settings.

- Create
  - View, create, and edit notifications for the phone
  - View, create, and edit reminders for the user
  - View, create, and edit cards for the app

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Dashboard tab
* User tab
* Create tab

**Flow Navigation** (Screen to Screen)

- Login screen
  - on sucessful login navigates to dashboard.
  - on account creation navigates to a sign-up screen, then back to login.

- Dashboard screen
  - Tap on the Attention Button:
    -Triggers a signal or displays a card fragment.
  - Tap on the mailbox:
    -Navigate to Card View.
  - Tap on love letter:
    -Navigate to love letter details screen.

- Create screen
  - Tap on the Notifications panel:
    -Navigate to create/edit notifications.
  - Tap on the Reminders panel:
    -Navigate to create/edit Reminders.
  - Tap on the Cards panel:
    -Navigate to create/edit Cards.

- User screen
  - Tap on the Analytics panel:
    -Navigate to metrics Details.
  - Tap on the Edit Account panel:
    -Navigate to Account Details.
  - Tap on the Settings panel:
    -Navigate to Settings View.

## Wireframes

[ An incredibly Crude mockup of the app but the overall intentions will remain the same even if designs are changed ] <img src="https://github.com/Venrite/zg59-CS388-001/blob/FinalProject/DYLM/DYLM.png" width=600>

<br>

<br>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

<br>

# Milestone 2 - Build Sprint 1 (Unit 8)

## GitHub Project board

[Add screenshot of your Project Board with three milestones visible in
this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

## Issue cards

- [Add screenshot of your Project Board with the issues that you've been working on for this unit's milestone] <img src="https://github.com/Venrite/zg59-CS388-001/blob/FinalProject/DYLM/image_2024-12-05_134739438.png" width=600>
- [Add screenshot of your Project Board with the issues that you're working on in the **NEXT sprint**. It should include issues for next unit with assigned owners.] <img src="https://github.com/Venrite/zg59-CS388-001/blob/FinalProject/DYLM/image_2024-12-05_134818644.png" width=600>

## Issues worked on this sprint

- Made the 3 screens with placeholder buttons, and made swipe navigation.
- [Add giphy that shows current build progress for Milestone 2. Note: We will be looking for progression of work between Milestone 2 and 3. Make sure your giphys are not duplicated and clearly show the change from Sprint 1 to 2.]<img src="https://github.com/Venrite/zg59-CS388-001/blob/FinalProject/DYLM/final%20prework%20demo.gif" width=600>
<br>

# Milestone 3 - Build Sprint 2 (Unit 9)

## GitHub Project board

[Add screenshot of your Project Board with the updated status of issues for Milestone 3. Note that these should include the updated issues you worked on for this sprint and not be a duplicate of Milestone 2 Project board.] <img src="https://github.com/Venrite/zg59-CS388-001/blob/Final/DoYouLoveMe/image_2024-12-09_210232099.png" width=600>

## Completed user stories

-Login, create an account, and sign out via firebase
-Can create "cards" saved locally
-API providing quotes to assist card generation
-Room database provides CRUD actions for cards
-View of cards provides overall view
-Able to expand the Cards for more details
-Persistent changable settings 
-Bottom nav bar to travel between the views (set up for easy expansion later)
-Haptic feedback for card creation and long press for deletion 
-Consistent UI Theme for both light and dark versions of the app
-Notifications of invalid operations via Toasts

-Abandoned Big red button concept
-Abandonded reminder section

[Add video/gif of your current application that shows build progress]
<img src="https://github.com/Venrite/zg59-CS388-001/blob/Final/DoYouLoveMe/final%20predemo.gif" width=600>

## App Demo Video

- Embed the YouTube/Vimeo link of your Completed Demo Day prep video
