## Value at Risk Calculator App

This app is a basic implementation of historical value at risk calculation for both singles trades and portfolios.
The approaches for calculating the Value at Risk are:

1. Single Trade: we sort the historical values and subsequently get percentile desired.
2. Portfolio: we sum the values of each trade corresponding to the same period of time, and for this we assume that the samples
have the same size to simplify the math. Then, we sort the resulting list representing the historical values and calculate
it the same way as the single trade.

## Setup

The dependencies of this project are managed through Gradle. There should not be any extra setup required. There is a
basic main method at ValueAtRiskApp with an example that can be run through IntelliJ or any other IDE. 
There are also a few simple unit tests under the test module.