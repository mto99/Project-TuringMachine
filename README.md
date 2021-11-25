# Project-TuringMachine


## Solving issues

### Error: "Not authorized" 
Your account credentials are invalid when pushing or fetching. The error message "...Not Authorized" occurs.

Solve:

First you have to create a token:

1. Go to your GitHub Settings and click on the left panel `Developer Settings`-> `Personal Access Token`
2. Enter a notice and select the scope you need. Then click `Generate new token`
3. Now copy the token. It looks like `3j98324f7892nfz8f7h84f8`

Now go back to your Eclipse project
1. Select `Window` -> `Show View` -> `Other...`. Under `Git` select `Git Repositories`
2. A new pane appears, from which you can open `<repo name>` -> `Remotes` -> `origin`
3. Right click on a node and choose `Change Credentials`.
4. Enter your username for `User` and your created token for `Password`.

After that the problem should be fixed.
