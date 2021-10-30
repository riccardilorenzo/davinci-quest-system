<h1>DaVinci Quest System</h1>
<h2>What is this?</h2>
<p>This is a project for a Quest System made for DaVinci Corporation, a Player-Controller Minor Faction based in the universe of Elite: Dangerous.
This project is made by a student, so has no claim to be used in a production environment, but only internally by this game faction.</p>

<h2>Structure</h2>
The structure tries to follow the MVC pattern:
<ul>
<li><b>Model: </b>under the model directory, we have basic classes mandatory to instantiate a Commander, a Mission, and every component Commander/Mission needs</li>
<li><b>View: </b>under the webapp directory</li>
<li><b>Controller: </b>under the controller directory. Should be reorganized in a single controller, maybe with an interface</li>
</ul>

Moreover, there is a ServletUtils class which is used to store some useful constants, such as connection string.
Finally, the <b>persistence</b> provides two interfaces: one for the Reader, and the other for the Writer. There is an implementation, too: DatabaseReader and DatabaseWriter, based both on PostgreSQL.

<h2>Final notes</h2>
<p>This project is still very Work In Progress, and therefore subject to significant changes in the future. Some parts still need to be written.
JavaDoc comments should be present in each method and class definition, so you will have a complete documentation just by creating it with a proper IDE.
I'm open to any submission or change proposal, so just fork this and submit changes.</p>
