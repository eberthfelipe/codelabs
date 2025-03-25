# codelabs
Google Code Labs implementation.

## Compose Concepts
- To add internal state to a composable, you can use the **mutableStateOf** function, which makes Compose recompose functions that read that **State**.
- To preserve state across recompositions, remember the mutable state using **remember**.
  - remember is used to guard against recomposition, so the state is not reset.
  - https://androidpro.io/whats-the-difference-between-remember-and-by-remember/
- 