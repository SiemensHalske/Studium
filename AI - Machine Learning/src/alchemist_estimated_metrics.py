    def calculate_parameters(hidden_size, num_attention_heads, num_blocks):
    params_per_block = []

    for _ in range(num_blocks):
        # Multihead Attention Layer Parameter
        attention_params = 4 * hidden_size**2
        # Feed-Forward Layer Parameter
        ff_params = (hidden_size**2 * 4) + (hidden_size * 4 * hidden_size)
        # Layer-Normalization Parameter
        ln_params = 2 * hidden_size
        # Dropout Parameter (prozentual)
        dropout_params = 0.1 * (attention_params + ff_params)

        # Gesamtzahl der Parameter in diesem Block
        total_params = attention_params + ff_params + ln_params + dropout_params

        params_per_block.append(total_params)

    # Gesamtzahl der Parameter für alle Blöcke
    total_params_all_blocks = sum(params_per_block)

    return params_per_block, total_params_all_blocks

# Beispielkonfiguration
hidden_size = 3072
num_attention_heads = 12
num_blocks = 6

params_per_block, total_params_all_blocks = calculate_parameters(hidden_size, num_attention_heads, num_blocks)

print("Parameter pro Block:", params_per_block)
print("Gesamtzahl der Parameter für alle Blöcke:", total_params_all_blocks)
