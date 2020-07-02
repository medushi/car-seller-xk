# Car Seller Kosova -Android App

>This project is for evaluation purposes only.It is a part of university assignment in the subject of "Mobile Programming".

## Requirements

>For testing the app you  must use an Android OS device with API version>= 16.

## Permissions and external libraries and services

>This app requires permission for Internet,Camera and Storage
>Besides default libraries we used picasso for loading images from url, open source round image implementation library.As for services app has been integrated with FireBase Services: Real Time Database and Storage

## Features
- Register to sell a car. Give a title to your post, choose specifics, desired price,location and upload up to three images from camera or gallery.
- View posts.We implemented custom recycler view for this.
- Add to favorites or buy (of course buy is not implemented);
- You can navigate to favorites to view your saved posts by their title.
- View your profile information and option to logout.
- View in map number of posts based on city using HERE SDK and clustering (not implemented)

## Project Structure 
>We tried to maintain seperation of concerns in terms of structuring project. We divided:

- Activities
- Fragments
- Models
- Helpers such as : Adapters,DatabaseHelpers, ValidationHelpers.

## Fulfillment of requirements based on assignment

- Multiple activities DONE
- Working with fragments DONE (ProfileActivity)
- Using various layouts, designs, animations etc DONE
- Use internal database (Sqlite) DONE (Saving favorite posts)
- Use shared preferences DONE (Keeping user logged in)
- Integrate Firebase Services (RTD,Storage) DONE
- Parsing response from API (NOT DONE).We planned to implement in Maps by getting geo cordinates of city via GEOCODE APi and use that response to draw markers in map
- Responsive in different screen sizes Partially done
- Permission configurations,toasts snackbars and menu or tab(we used navigationdrawer) DONE
