module.exports = app => {
    const tvs = require("../controllers/TV.controller.js");
  
    var router = require("express").Router();
  
    // Create a new TV
    router.post("/", tvs.create);
  
    // Retrieve all TVs
    router.get("/", tvs.findAll);
  
    // Retrieve a single TV with id
    router.get("/:id", tvs.findOne);
  
    // Update a TV with id
    router.put("/:id", tvs.update);
  
    // Delete a TV with id
    router.delete("/:id", tvs.delete);
  
    // Delete all TV
    router.delete("/", tvs.deleteAll);
  
    app.use('/api/tvs', router);
  };