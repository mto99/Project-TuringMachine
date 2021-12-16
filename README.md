# Project-TuringMachine


## JSON-File Syntax

```
{
	"tape": <chars in String,no spaces>,
	"alphabet": <chars in String,no spaces>,
	"startState":<statename>,
	"startPosition": <startposition>,
	"allStates": <All states in Array>,
	"acceptStates": <states in Array>,
	"rejectStates": <states in Array>,
	
	"transitionFunction":
		<Array of Transition Functions>
		
		
}

TransitionFunction
{
	"previousState" : <statename>,
	"readSymbol" : <char>, 
	"newState" : <statename>,
	"writtenSymbol" : <char>,
	"movement" : <char:L|R|N>
},

```

### Example

```
{
	"alphabet": "AVLX",
	"tape":"LXVX",
	"startState":"replace",
	"startPosition": "last",
	"allStates":["replace","end"],
	"rejectStates":[" "],
	"acceptStates": ["end"],
	
	"transitionFunction":[
		{
			"previousState" : "replace",
			"readSymbol" : "L", 
			"newState" : "replace",
			"writtenSymbol" : " ",
			"movement" : "L"
		},
		{
			"previousState" : "replace",
			"readSymbol" : "V", 
			"newState" : "replace",
			"writtenSymbol" : " ",
			"movement" : "L"
		},
		{
			"previousState" : "replace",
			"readSymbol" : "X", 
			"newState" : "replace",
			"writtenSymbol" : "A",
			"movement" : "L"
		},
		{
			"previousState" : "replace",
			"readSymbol" : " ", 
			"newState" : "end",
			"writtenSymbol" : " ",
			"movement" : "N"
		},
		{
			"previousState" : "end",
			"readSymbol" : " ", 
			"newState" : "end",
			"writtenSymbol" : " ",
			"movement" : "N"
		}
		
	]
}
```
