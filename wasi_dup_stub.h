// wasi_dup_stub.h — stub for dup() when building tree-sitter for wasm32-wasip1.
// tree-sitter's tree.c references dup() in debug-only paths. wasi-libc does
// not provide dup(), so we stub it here via -include in CFLAGS.
#pragma once
#ifdef __wasi__
#include <unistd.h>
static inline int dup(int fd) { (void)fd; return -1; }
#endif
