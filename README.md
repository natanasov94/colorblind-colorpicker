# Colorblind colorpicker
As a colorblind person trying to learn pixel art, i've had struggles just trying to select the "base" color for me to use. 

An example question i've had is `I want to color leaves, which color is green` and i somehow choose a brown color. Yes, i could have copied the RGB values and asked good old Copilot/ChatGPT what the color is, but that was tedious

So, i decided i want to create a very simple, and, hopefully usable color picker that sole purpose isn't to copy hex/rgb
values into clipboard, but instead interprets what the color you have selected is, and tells you in plain English.

The best method so far i've found, is to create a HSV decision tree, and use that one, with possible tweaks. I don't need much colors, i just need to figure out `is this a green or is this a brown color`, i don't need the actual "fancy" name.

![color_wheel](docs/color_wheel.png "Color wheel")