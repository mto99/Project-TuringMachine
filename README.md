# Project-TuringMachine


## JSON-File Syntax

```
{
	"inputTape": <chars in JSONArray>,
	"currentState":<statename>,
	"startPosition": <startposition>,
	"allStates": <All states in JSONArray,
	
	"transitionFunction":{
	
		<statename>:{
			<input>: [<output>,<movement>,<nextState>],
			<input>: [<output>,<movement>,<nextState>],
			...
		},
		
		<statename>:{
			<input>: [<output>,<movement>,<nextState>],
			...
		},
		
		...
		
	}
  
}

```

### Example

```
{
	"inputTape":["0","1","1","0","0"],
	"currentState":"scan",
	"startPosition": "0",
	"allStates":["scan","carry","end"],
	
	"transitionFunction":{
	
		"scan":{
			"1": ["","R",""],
			"0": ["","R",""],
			" ": ["","L","carry"]
		},
		
		"carry":{
			"1": ["0", "L", ""],
			"0": ["1", "L", "end"],
			" ": ["1", "L", "end"]
		},
		
		"end":{
		}
		
	}
```
