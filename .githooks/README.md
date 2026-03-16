# Git hooks del proyecto

Este proyecto incluye un hook `pre-push` que ejecuta `mvn verify`.

Si fallan los tests o la cobertura definida por JaCoCo en `pom.xml`, el push se bloquea.

## Activacion (una sola vez por clon)

```bash
git config core.hooksPath .githooks
```

## Umbral de cobertura

- Por defecto: `0.90` (90%) via propiedad `coverage.minimum` en `pom.xml`.
- Puedes exigir 100% puntualmente:

```bash
./mvnw verify -Dcoverage.minimum=1.00
```

