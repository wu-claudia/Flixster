# Flixster
# Pre-work - Flixster

Flixster is an android app that allows users to view movies and learn about their synopses in a scrollable list. After clicking on a particular movie, users can also learn more details about a particular movie, like its release date and rating, as well as access the video trailer from YouTube.

Submitted by: Claudia Wu

Time spent: 12 hours spent in total

## User Stories

The following required functionality is completed:

* [x] User can scroll through movies from the list on the welcome page

The following additional features are implemented:

* [x] User can tap a movie item in the list and bring up an additional screen for the movie item to view extra details about the movie item
* [x] User can view the movie poster in the portrait mode and the movie backdrop in the landscape mode
* [x] Optimized list adapters for performance with the ViewHolder pattern
* [x] Displays default image during loading
* [x] Allows user to access the trailer for the movie from YouTube

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/WMUfnRf.gif' title='Video Walkthrough' width='' alt='Video Walkthrough for Portrait Mode' />

<img src='http://i.imgur.com/b0NAUdu.gif' title='Video Walkthrough' width='' alt='Video Walkthrough for Landscape Mode' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Challenges:
* Using the Movie API to get certain details about the movie, like its rating, backdrop path, and poster path, then trying to apply it in the actual page view
* Learning how to use Picasso to load different images for when the app is in portrait or landscape mode
* Integrating additional design features to improve some features that did not work before and overlaying new features on top of old ones for further improvement

## License

    Copyright 2016 Claudia Wu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
