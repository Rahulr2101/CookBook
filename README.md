# Recipe App - Android

This Android Recipe App allows users to search for food recipes, receive food recommendations, and sort results using various filters. The app is built in Java and integrates with [TheMealDB API](https://www.themealdb.com/) to fetch recipe data such as ingredients, instructions, and images.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Setup](#setup)
- [Sorting Options](#sorting-options)
- [Screenshots](#screenshots)


## Features
- **Search Recipes**: Search by meal name, ingredient, or meal type (breakfast, lunch, dinner, etc.).
- **Food Recommendations**: Get personalized recommendations or view random popular recipes.
- **Sorting and Filtering**: Sort recipes by cuisine, meal type, and ingredients.
- **Meal Details**: View detailed information for each recipe, including ingredients, preparation instructions, and images.

## Technologies
- **Java**: Core programming language used for Android app development.
- **TheMealDB API**: API used for fetching recipe data.
- **Android SDK**: Used for developing the mobile app.
- **Retrofit**: HTTP client for making API requests.
- **Picasso**: Library for loading images from URLs into `ImageView`.
- **JSON**: Used to parse API responses.

## Sorting-Options
- **Sort by Categories**: Easily sort recipes by meal categories such as:
  - **Breakfast**
  - **Dessert**
  - **Starter**
  
- **Sort by Meal Type**: Filter recipes based on specific types, including:
  - **Desserts**
  - **Cold Drinks**

- **Advanced Search**: Quickly search for recipes by meal name, ingredient, or category. For example, search for "Pasta" or filter by ingredients like "Chicken" or "Tomato."


## Setup

### Prerequisites
- **Android Studio**: Download and install [Android Studio](https://developer.android.com/studio).
- **Java 8 or higher**: Ensure Java is set up in your Android Studio environment.
- **Gradle**: Android project dependency manager.

### Clone the Repository
```bash
git clone https://github.com/your-username/recipe-app.git
cd recipe-app
