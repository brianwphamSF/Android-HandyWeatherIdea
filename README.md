This is an Android weather application.

Play store link: goo.gl/Fcuiov

Libraries needed to compile this project:

GraphView, found here: https://github.com/jjoe64/GraphView
MapQuest Maps, found here: http://goo.gl/wtpOEv

This application uses the Open Weather Map API. The keys that are needed in the res/values/strings.xml file are:

    <string name="open_weather_maps_app_id">OPEN_WEATHER_MAP_KEY</string>
    <string name="mapquest_key">MAPQUEST_API_KEY</string>

Please replace OPEN_WEATHER_MAP_KEY and MAPQUEST_API_KEY with your keys.

Map loading is super, the plots from Open Weather Map take a while in its API call.
Upon everything else, all is working nicely.
