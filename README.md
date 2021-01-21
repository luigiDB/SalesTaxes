# salesTaxes

Simple java project to evaluate the bill for a cart considering taxes.

## CI
Continuous integration maintained on github CI reachable under Actions tab of this project
or [Here](https://github.com/luigiDB/salesTaxes/actions).

The CI configuration is stored in the repo in [.github/workflows/unitTests.yml](https://github.com/luigiDB/salesTaxes/blob/master/.github/workflows/unitTests.yml).

The CI job start on each push and executes 
1. Unit tests
2. Coverage (for this repo is set to 100% with Jacoco) 
3. Save the surefire test results ar build artifact that can be downloaded directly from the build page.



