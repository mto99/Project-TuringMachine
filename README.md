# Project-TuringMachine


## JSON-File Syntax

```
{
	"alphabet": "<chars in String,no spaces>",
	"startState":"<statename>",
	"startPosition": "<startposition>",
	"allStates": "<All states in Array>",
	"rejectStates": ["<states in Array>"],
	"acceptStates": ["<states in Array>"],
	"transitionFunction":[
		{
			<Array of Transition Functions>
		}
	]
}

```

### Array of Transition Functions
```
	"transitionFunction":[
	{
		"previousState" : "<statename>",
		"readSymbol" : "<char>", 
		"newState" : "<statename>",
		"writtenSymbol" : "<char>",
		"movement" : "<char:L|R|N>"
	}
	],
	"tape": "<chars in String,no spaces>"
}

```

### Example

```
{
	"alphabet": "AVLX",
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
		
	],
	"tape":"LXVX",
}
```
