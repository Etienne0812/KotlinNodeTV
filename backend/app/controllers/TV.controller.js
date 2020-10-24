const db = require("../models");
const TV = db.tvs;
const Op = db.Sequelize.Op;

// Create and Save a new Bicycle
// req --> request (contains the body)
exports.create = (req, res) => {
  // Validate request
  if (!req.body.brand || !req.body.model) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a Bicycle
  const tv = {
    brand: req.body.brand,
    model: req.body.model, 
    price: req.body.price
  };

  // Save Bicycle in the database
  TV.create(tv)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Bicycle."
      });
    });
};

// Retrieve all Bicycles from the database.
exports.findAll = (req, res) => {
  
    TV.findAll()
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving bicycles."
        });
      });
};

// Find a single Bicycle with an id
exports.findOne = (req, res) => {
  let id = req.params.id;
  TV.findByPk(id)
    .then(data => {
      console.log("estos son los datos")
      console.log(data);
      if(!data){
        res.status(400).send({
          message:
            "No TV found with that id"
        })
      }
      res.send(data);
      return;
    })
    .catch(err => {
      console.log(err.message);
      console.log("hola");
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving TV with id"
      });
      return;
    });
};

// Update a Bicycle by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  TV.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "TV was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update TV with id=${id}. Maybe TV was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating TV with id=" + id
      });
    });
};

// Delete a Tutorial with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  TV.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "TV was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete TV with id=${id}. Maybe TV was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not delete TV with id=" + id
      });
    });
};

// Delete all Bicycles from the database.
exports.deleteAll = (req, res) => {
  TV.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} TVs were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all TVs."
      });
    });
};