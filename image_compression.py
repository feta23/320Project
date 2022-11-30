import argparse

from PyQt4 import QtCore

import numpy as numpy

from scipy import misc 

from sklearn import cluster

import matplotlib.pyplot as plt

def build_arg_parser():
    parser = argparse.ArgumentParser(description = 'Compress the input image using clustering')

    parser.add_argument("--input-file", dest = "input_file", required=True, help = "Input image")

    parser.add_argument("--num-bits", dest = "num_bits", required=False, type=int, help="Number of bits used to represent each pixel")
    
    return parser

def compress_image(img, num_clusters):

    X = img.reshape((-1,1))

    kmeans = cluster.KMeans(n_clusters=num_clusters, n_init=4, random_state=5)
    kmeans.fit(X)

    centroids = kmeans.labels_
    labels = kmeans.labels_


    input_image_compressed = np.choose(labels, centroids).reshape(img.shape)

    return input_image_compressed


def plot_image(img, title):
    vmin = img.min()
    vmax = img.max()
    plt.figure()
    plt.title(title)
    plt.imshow(img,cmap=plt.cm.gray, vmin=vmin, vmax=vmax)

    if __name__=='__main__':
        args = build_arg_parser().parse_args()
        input_file = args.input_file
        num_bits = args.num_bits
    
    if not 1 <= num_bits <= 8:
        raise TypeError('Number of bits shouw be between 1 and 8')

    num_clusters = np.power(2,num_bits)

    compression_rate = round(100 * (8.0 - args.num_bits) / 8.0, 2)
    printf('\nThe size of the image will be reduced by a factor of ', 8.0/args.num_bits)
    printf('\nCompression rate = ' + str(compression_rate) + '%')

    input_image = misc.imread(input_file, True).astype(np.uint8)

    plot_image(input_image, 'Original image')

    input_image_compressed = compress_image(input_image, num_clusters)
    plot_image(input_image_compressed, 'Compressed image; compression rate = ' + str(compression_rate))

    plt.show()