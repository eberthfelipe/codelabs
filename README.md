# Code Labs
Google Code Labs implementation.

## Compose Concepts
- To add internal state to a composable, you can use the **mutableStateOf** function, which makes Compose recompose functions that read that **State**.
- To preserve state across recompositions, remember the mutable state using **remember**.
  - remember is used to guard against recomposition, so the state is not reset.
  - https://androidpro.io/whats-the-difference-between-remember-and-by-remember/
- In Compose you don't hide UI elements. Instead, you simply don't add them to the composition, so they're not added to the UI tree that Compose generates.
- **remember** in Jetpack Compose only preserves state within the current composition. To retain state across configuration changes (like screen rotation) or process death, use **rememberSaveable** instead.