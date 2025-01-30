#Shared (among features) codebase

In order to avoid the circular dependency problem, we have a shared codebase that
is used by all features but does not depend on any of them. 

The responsibilities of this module:

 - hold common bottom bar menu items with its assets `shared/src/main/res/menu/bottom_nav_menu.xml`
 - setup the bottom navigation bar according to active feature or screen within the feature
   (search/favorites within vacancies feature). Because the module must not depend on any feature,
   the navigation is implemented using strin

```
intent.component = ComponentName("solutions.s4y.effectivem", "solutions.s4y.effectivem.hotels.HotelsActivity")
intent.data = Uri.parse("effectivem://hotels")
startActivity(intent)
```