# Classified Ads
This is designed as a backend for classified ads website with the following features:
* Register and login
* Post items to sell 
* Post wanted items
* Buy advertised items 
* Add items to wishlist
* Search items


## List of REST Endpoints
### Category
- Create new category without super category (**POST** /api/categories)
```
{
    "name": "Electronics",
    "parentId": null
}
```
- Create new category as category (**POST** /api/categories)
```
{
    "name": "Smartphones",
    "parentId": 1
}
```
- List all categories (**GET** /api/categories)
- Get a specific category details (**GET** /api/categories/{id})

### Advertisement
- Create new advertisement (**POST** /api/advertisements)
```
{
    "type": "Offer",
    "category": {
        "id": 1,
        "name": "Smartphones",
        "parentId": 1
    },
    "title": "iPhone XX ",
    "description": "new, not yet opened",
    "price": 700,
    "location": "Frankfurt am Main"
}
```
- List all advertisements (**GET** /api/advertisements)
- Get a specific advertisement details (**GET** /api/advertisements/{id})

### Wishlist
- Add new item in a user's wishlist (**POST** /api/users/{userId}/notepad)
```
{
      
        "ad": {
            "id": 4
        },
        "note": "Christmast present"
    }
```
- List all items in a user's wishlist (**GET** /api/users/{userId}/notepad)
- Delete an item in a user's wishlist (**DELETE** /api/users/{userId}/notepad/{id})

### User
- Add a new user (**POST** /api/users)
```
{
    "email": "test@testmail.de",
    "password": "testpass",
    "first_name": "Test",
    "last_name": "Testman",
    "phone": "12345678",
    "location": "Testtown",
}
```
- List all items in a user's wishlist (**GET** /api/users)
- Get a specific user details (**GET** /api/users/{userId})
