import tensorflow as tf
from keras.models import load_model

# Load the PB model
model_pb = tf.saved_model.load('saved_model.pb')

# Convert the PB model to H5 format
model_h5 = tf.saved_model.save(model_pb, 'saved_model.h5')
