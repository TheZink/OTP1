## Basic Instructions

All project sprintreviews can be found under the [doc](https://github.com/TheZink/OTP1/tree/main/doc) folder

## Localisation Instructions

Currently, the application localisation works by selecting one of 4 language from a drop down menu. These languages include:
- English
- Finnish
- Japanese
- Persian

Language change happens while logging in and will be in effect while using the application. Admins will also see the language change in the adminpanel. Language cannot be changed afterwards while using the application.

If the user has been chosen a language before hand, upon signing in the pages will automatically translate to the chosen language. If the user manually chooses another language in the login page, the language will change to the new one. 
In addition, it is planned for automatic saving to the database depending on the login choice (TBA)

## Localisation Resources

For localisation, translations have been provided from [Google Translate](https://translate.google.fi/?sl=auto&tl=fi&op=translate) and [DeepL](https://www.deepl.com/en/translator)
Localisation is done through ResourceBundles and all translated words are stored as keys. There is a possibility to use database for translation and a SQL script has been provided inside the scripts folder

## SonarQube Latest Report

<img width="1194" height="498" alt="kuva" src="https://github.com/user-attachments/assets/2e50f72b-49f4-4c08-a823-f15f7e412249" />

## Other resources
[Product Documentation]()
[Project Development Process]()
[JavaDoc](https://github.com/TheZink/OTP1/tree/main/doc/JavaDoc)
[Trello](https://trello.com/b/5WAxql7T/product-backlog-board)
[Project Plan](https://docs.google.com/document/d/1O_g-zJz1wV71_avTI1PDZ-5DXeloIw9V7ZhQsQWpBLM/edit?tab=t.0)
[Product Vision](https://docs.google.com/document/d/1O3U6CPSf3AjObtddmFEt4F05RRHxHQHc2p4wla8N3JY/edit?tab=t.0)
[Figma Blueprint](https://www.figma.com/design/2FSC3hxvOkFGg2zG9coUL4/SchoolApp?node-id=0-1&p=f&t=2K1rck9SdNKwUYTN-0)
