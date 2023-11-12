using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GraphTransformerBlock
{
    public class GraphTransformerBlock
    {
        private readonly int _hiddenDim;
        private readonly int _numHeads;
        private readonly float _dropout;

        private readonly AttentionHead _textAttention;
        private readonly AttentionHead _imageAttention;
        private readonly AttentionHead _relationAttention;
        private readonly FeedForwardNetwork _ffn;

        public GraphTransformerBlock(int hiddenDim, int numHeads, float dropout)
        {
            _hiddenDim = hiddenDim;
            _numHeads = numHeads;
            _dropout = dropout;

            _textAttention = new AttentionHead(_hiddenDim, _numHeads);
            _imageAttention = new AttentionHead(_hiddenDim, _numHeads);
            _relationAttention = new AttentionHead(_hiddenDim, _numHeads);
            _ffn = new FeedForwardNetwork(_hiddenDim, _hiddenDim * 4, _hiddenDim);
        }

        public Tensor Forward(Tensor x, Tensor edgeIndex)
        {
            // Text-Attention
            var textAttentionOutputs = _textAttention.Forward(x, x, x, null);

            // Bild-Attention
            var imageAttentionOutputs = _imageAttention.Forward(x, x, x, null);

            // Beziehungs-Attention
            var relationAttentionOutputs = _relationAttention.Forward(x, x, edgeIndex, null);

            // Feed-forward network
            var ffnOutputs = _ffn.Forward(textAttentionOutputs + imageAttentionOutputs + relationAttentionOutputs);

            return x + ffnOutputs;
        }
    }

    public class AttentionHead
    {
        private readonly int _hiddenDim;
        private readonly int _numHeads;

        public AttentionHead(int hiddenDim, int numHeads)
        {
            _hiddenDim = hiddenDim;
            _numHeads = numHeads;
        }

        public Tensor Forward(Tensor query, Tensor key, Tensor value, Tensor attentionMask)
        {
            // Aufteilen der Eingaben in Heads
            var querySplit = query.Split(_numHeads);
            var keySplit = key.Split(_numHeads);
            var valueSplit = value.Split(_numHeads);

            // Berechnung der Aufmerksamkeitsgewichte
            var attentionWeights = AttentionMechanism.ComputeAttentionWeights(querySplit, keySplit, attentionMask);

            // Multiplikation der Aufmerksamkeitsgewichte mit den Werten
            var attentionOutputs = attentionWeights.MatMul(valueSplit);

            // Zusammenführen der Heads
            var attentionOutputsConcat = attentionOutputs.Concat(_numHeads);

            return attentionOutputsConcat;
        }
    }

    public class FeedForwardNetwork
    {
        private readonly int _inputDim;
        private readonly int _outputDim;

        public FeedForwardNetwork(int inputDim, int outputDim)
        {
            _inputDim = inputDim;
            _outputDim = outputDim;
        }

        public Tensor Forward(Tensor input)
        {
            // Lineare Transformation 1
            var linear1Outputs = input.MatMul(new Tensor(new float[_inputDim, _outputDim]));

            // ReLU-Aktivierungsfunktion
            var reluOutputs = linear1Outputs.Relu();

            // Lineare Transformation 2
            var linear2Outputs = reluOutputs.MatMul(new Tensor(new float[_outputDim, _inputDim]));

            return linear2Outputs;
        }
    }
}
