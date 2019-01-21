# Mint ViewBinder ![](https://img.shields.io/bintray/v/syedowaisali/maven/mint-view-binder.svg)   [![GitHub issues](https://img.shields.io/github/issues/syedowaisali/mint-view-binder.svg)](https://github.com/syedowaisali/mint-view-binder/issues)   [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)



- A lightweight runtime ViewBinder.
- No code generation, uses reflection to bind references from xml.
- Simple annotations to eliminate findViewById calls.
- OnClick annotations to avoid setOnClickListener calls.
- **No need to declare fields public in order to expose them to binders**

*Why use this when we have butterknife?*

This library just includes ViewBinder and OnClick binding sources, which most of the project use. ButterKnife comes with a lot of other features that project rarely uses.


## Release
Available Version:  1.0.0 on [jcenter](https://bintray.com/syedowaisali/maven/mint-view-binder/1.0.0) 


## Library Source
[Jump to library source.](https://github.com/syedowaisali/mint-view-binder/tree/master/mint-view-binder/src/main/java/net/crystalapps/mint/viewbinder/library)

## Getting Started
### Prerequisites

Minimum API Level is 21. 

### Adding the library


In your app level gradle **(4.0+)**, add:
```
    implementation 'net.crystalapps:mint-view-binder:1.0.0'
```
for gradle versions **below 4.0** use:
```
    compile 'net.crystalapps:mint-view-binder:1.0.0'
```
## Using in your project

- For simple view binding:
```
    @BindView( R.id.loginButton )
    private Button loginButton;
```    
- For binding multiple views:
```    
     @BindViews( { R.id.usernameTextView, R.id.statusTextView} )
     private TextView[] textViews;
```    

- For binding click listener:
```    
     @OnClick( { R.id.loginButton, R.id.signUpButton} )
     private onClick(View v){
     
        switch(v.getId()){
            
            case R.id.loginButton:
                // do something
                break;
               
            case R.id.signUpButton:
                // do something
                break;
               
        
        }
     
     }
```    



## Contributing

Contributions are welcomed as long as they dont break the code. Please create an issue and have a discussion before pull request.

## Hosting

Hosted at JCenter: https://bintray.com/syedowaisali/maven/mint-view-binder

## Authors

* **Owais Ali** - *Initial work* - [@syedowaisali](https://github.com/syedowaisali)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](https://github.com/syedowaisali/mint-view-binder/blob/master/LICENSE) file for details.

*Sources from Android and Android APIs are subject to the licensing terms as per Android Open Source Project (AOSP).*

