# basalt-plugin-parser-kotlin

Tree-sitter Kotlin parser plugin for Basalt.

## Capabilities

- `SYNTAX_PARSER`: syntax highlighting and scope extraction for `*.kt` and
  `*.kts` (through the `kts:kt` extension alias).
- `parse.syntax@kt/v1`: keyword/string/number/comment/type/function spans.
- `parse.retrieval@kt/v1`: retrieval chunks for classes, objects, functions,
  properties, type aliases, packages, and imports.
- `parse.call-sites@kt/v1`: bare calls and receiver-qualified calls.

## Building

Parsers target `wasm32-wasip1` because they load into core's WASI-enabled
parser linker:

```bash
CC_wasm32_wasip1=/path/to/wasi-sdk/bin/clang \
AR_wasm32_wasip1=/path/to/wasi-sdk/bin/llvm-ar \
CFLAGS_wasm32_wasip1="--sysroot=/path/to/wasi-sdk/share/wasi-sysroot -include $PWD/wasi_dup_stub.h" \
cargo build --release --target wasm32-wasip1
```

Publish the artifact as `kt.wasm`.

## Tests

```bash
cargo test --target <host-triple>
cargo test --target <host-triple> -- --ignored   # parses real add-helper sources
```
