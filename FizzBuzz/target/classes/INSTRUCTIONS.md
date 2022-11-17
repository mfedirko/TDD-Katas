### The FizzBuzz Kata
	- Write a program that prints the numbers from 1 to 100. But for multiples of three print "Fizz"
	  instead of the number and for the multiples of five print "Buzz". For numbers which are multiples of both
	  three and five print "FizzBuzz".

### Including new implementation to The FizzBuzz Kata
	- Added a different implementation for FizzBuzz using Reactive extensions.
	- The changes are described at: http://blog.drorhelper.com/2015/02/fizzbuzz-tdd-kata-using-reactive.html
	- Pull Request by: https://github.com/dhelper (manually merged by @garora)
	  

#### Steps:

	Lets divide this into different steps so, we can easily write and test this.
	- Print numbers from 1 to 100
	- Print "Fizz" instead of number which is divisible by 3
	- Print "Buzz" instead of number which is divisible by 5
	- Print "FizzBuzz" instead of number which is divisible by both 3 and 5

#### Make more test for accept numbers and provide results
	- Create a method to accept single number
	- Create test to verify supplied number within the range 1 to 100
	- Create test to verify number and return result Fizz or Buzz or FizzBuzz per above criteria
