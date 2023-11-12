import torch.nn as nn
import torch


class TransformerBlock(nn.Module):
    def __init__(self, hidden_size, num_attention_heads, num_embeddings):
        super(TransformerBlock, self).__init__()

        # Embedding layer
        self.embedding = nn.Embedding(num_embeddings, hidden_size)

        # MultiheadAttention layer
        self.attention = nn.MultiheadAttention(
            hidden_size, num_attention_heads)

        # Feed-forward layer
        self.feed_forward = nn.Sequential(
            nn.Linear(hidden_size, hidden_size * 4),
            nn.ReLU(),
            nn.Linear(hidden_size * 4, hidden_size)
        )

        # Normalization layers
        self.norm1 = nn.LayerNorm(hidden_size)
        self.norm2 = nn.LayerNorm(hidden_size)

        # Dropout
        self.dropout = nn.Dropout(p=0.1)

    def forward(self, input, attention_mask=None):
        """Forward pass of the transformer block.

        Args:
            input: A tensor of shape [batch_size, seq_len, embedding_dim] containing the input sequence.
            attention_mask: An optional tensor containing a mask to prevent attention to certain positions.

        Returns:
            A tensor of shape [batch_size, seq_len, hidden_size] containing the output sequence.
        """

        seq_len = input.size(1)  # Assuming input is [batch_size, seq_len, embedding_dim]
        d_model = self.embedding.embedding_dim  # Assuming this attribute exists and is the embedding dimension

        embedded_input = self.embedding(input)
        positional_encodings = self.positional_encodings(seq_len, d_model)  # Generate positional encodings
        embedded_input = embedded_input + positional_encodings  # Add positional encodings to the embedded input

        # Apply attention mask if provided
        attention_output, _ = self.attention(
            embedded_input, embedded_input, embedded_input, key_padding_mask=attention_mask
        )

        # Apply normalization and dropout
        x = self.norm1(embedded_input + self.dropout(attention_output))
        feed_forward_output = self.feed_forward(x)
        output = self.norm2(x + self.dropout(feed_forward_output))

        return output

    def positional_encodings(self, seq_len, d_model):
        # Create a tensor to hold the positional encodings
        position_encodings = torch.zeros(seq_len, d_model)

        # Generate the positional encodings
        for position in range(seq_len):
            for i in range(0, d_model, 2):
                pos = torch.tensor(position, dtype=torch.float)
                i_ = torch.tensor(i, dtype=torch.float)
                position_encodings[position, i] = torch.sin(pos / (10000 ** (2 * i_ / d_model)))
                position_encodings[position, i + 1] = torch.cos(pos / (10000 ** (2 * i_ / d_model)))

        # If you're using a device like CUDA, ensure the encodings are on the correct device
        if self.device:
            position_encodings = position_encodings.to(self.device)

        return position_encodings
    
    
if __name__ == '__main__':
    # run a little self-test, if
    # the transformer-block is working correctly
    
    # create a random input tensor
    input = torch.randn(10, 20, 30).long()  # Convert input tensor to long
    
    # create the transformer-block
    transformer_block = TransformerBlock(1500000, 3, 30)
    
    # run the input through the transformer-block
    output = transformer_block(input)
    
    # check the output shape
    assert output.shape == input.shape, \
        f'Expected output shape {input.shape}, got {output.shape} instead'
    
    print('Success! The transformer-block is working correctly')